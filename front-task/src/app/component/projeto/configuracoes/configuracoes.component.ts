import { CommonModule } from '@angular/common';
import { Component, Input, OnInit } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { Projeto } from 'src/app/models/Projeto';

interface Column {
  id: number;
  name: string;
  wipLimit: number | null;
  color: string;
}

interface TeamMember {
  id: number;
  name: string;
  email: string;
  role: string;
}

interface Tag {
  id: number;
  name: string;
  color: string;
}

interface GeneralSettings {
  autoAssign: boolean;
  estimationRequired: boolean;
  dueDateRequired: boolean;
  allowSubtasks: boolean;
  enableTimeTracking: boolean;
  notifyOnWipExceed: boolean;
  cycleTimeAnalysis: boolean;
}

@Component({
  selector:    'app-configuracoes',
  templateUrl: './configuracoes.component.html',
  styleUrls: [ './configuracoes.component.css'],
  standalone: true,
  imports:[ RouterModule, CommonModule, ReactiveFormsModule, FormsModule]
})
export class ConfiguracoesComponent implements OnInit {

 @Input() projeto!: Projeto;

  projectName: string = 'Meu Projeto';
  
  columns: Column[] = [
    { id: 1, name: 'Backlog', wipLimit: null, color: '#64748b' },
    { id: 2, name: 'To Do', wipLimit: 5, color: '#3b82f6' },
    { id: 3, name: 'In Progress', wipLimit: 3, color: '#f59e0b' },
    { id: 4, name: 'Review', wipLimit: 2, color: '#8b5cf6' },
    { id: 5, name: 'Done', wipLimit: null, color: '#10b981' }
  ];
  
  teamMembers: TeamMember[] = [
    { id: 1, name: 'João Silva', email: 'joao@email.com', role: 'Developer' },
    { id: 2, name: 'Maria Santos', email: 'maria@email.com', role: 'Designer' }
  ];
  
  tags: Tag[] = [
    { id: 1, name: 'Bug', color: '#ef4444' },
    { id: 2, name: 'Feature', color: '#10b981' },
    { id: 3, name: 'Urgent', color: '#f59e0b' }
  ];
  
  generalSettings: GeneralSettings = {
    autoAssign: false,
    estimationRequired: true,
    dueDateRequired: false,
    allowSubtasks: true,
    enableTimeTracking: true,
    notifyOnWipExceed: true,
    cycleTimeAnalysis: true
  };

  roles: string[] = ['Developer', 'Designer', 'Project Manager', 'QA', 'DevOps'];

  constructor() { }

  ngOnInit(): void {
  }

  // Métodos para Colunas
  addColumn(): void {
    const newColumn: Column = {
      id: Date.now(),
      name: 'Nova Coluna',
      wipLimit: null,
      color: '#6b7280'
    };
    this.columns.push(newColumn);
  }

  updateColumn(id: number, field: keyof Column, value: any): void {
    const columnIndex = this.columns.findIndex(col => col.id === id);
    if (columnIndex !== -1) {
      (this.columns[columnIndex] as any)[field] = value;
    }
  }

  deleteColumn(id: number): void {
    this.columns = this.columns.filter(col => col.id !== id);
  }

  moveColumn(fromIndex: number, toIndex: number): void {
    if (toIndex >= 0 && toIndex < this.columns.length) {
      const movedColumn = this.columns.splice(fromIndex, 1)[0];
      this.columns.splice(toIndex, 0, movedColumn);
    }
  }

  // Métodos para Membros da Equipe
  addTeamMember(): void {
    const newMember: TeamMember = {
      id: Date.now(),
      name: '',
      email: '',
      role: 'Developer'
    };
    this.teamMembers.push(newMember);
  }

  updateTeamMember(id: number, field: keyof TeamMember, value: any): void {
    const memberIndex = this.teamMembers.findIndex(member => member.id === id);
    if (memberIndex !== -1) {
      (this.teamMembers[memberIndex] as any)[field] = value;
    }
  }

  deleteTeamMember(id: number): void {
    this.teamMembers = this.teamMembers.filter(member => member.id !== id);
  }

  // Métodos para Tags
  addTag(): void {
    const newTag: Tag = {
      id: Date.now(),
      name: 'Nova Tag',
      color: '#6b7280'
    };
    this.tags.push(newTag);
  }

  updateTag(id: number, field: keyof Tag, value: any): void {
    const tagIndex = this.tags.findIndex(tag => tag.id === id);
    if (tagIndex !== -1) {
      (this.tags[tagIndex] as any)[field] = value;
    }
  }

  deleteTag(id: number): void {
    this.tags = this.tags.filter(tag => tag.id !== id);
  }

  // Salvar configurações
  saveConfiguration(): void {
    const config = {
      projectName: this.projectName,
      columns: this.columns,
      teamMembers: this.teamMembers,
      tags: this.tags,
      generalSettings: this.generalSettings
    };
    
    console.log('Configuração salva:', config);
    
    // Aqui você faria a chamada para o serviço para salvar no backend
    // this.projectService.saveConfiguration(config).subscribe(...)
    
    alert('Configurações salvas com sucesso!');
  }
}