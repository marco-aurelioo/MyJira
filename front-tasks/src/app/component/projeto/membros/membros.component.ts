import { Component, Input, OnInit } from '@angular/core';
import { Profile } from 'src/app/models/Profile';
import { Projeto } from 'src/app/models/Projeto';
import { Tarefa } from 'src/app/models/Tarefa';


@Component({
  selector: 'app-membros',
  templateUrl: './membros.component.html',
  styleUrls: ['./membros.component.css']
})
export class MembrosComponent implements OnInit{

  @Input() projeto!: Projeto;

  users: Profile[] = [];
  filteredUsers: Profile[] = [];
  searchTerm: string = '';
  filterType: string = '';

  ngOnInit() {
    this.loadUsers();
  }

  loadUsers() {
    // Dados de exemplo - substitua pela sua chamada de API
    this.users = [
      {
        userId: '001',
        name: 'João Silva',
        avatar: 'https://via.placeholder.com/50',
        characteristics: ['Frontend', 'React', 'Angular'],
        tasks: [
          { id: '1', titulo: 'Implementar login', status: 'completed', descricao: 'Sistema de autenticação' },
          { id: '2', titulo: 'Criar dashboard', status: 'in-progress', descricao: 'Painel principal' },
          { id: '3', titulo: 'Testes unitários', status: 'pending', descricao: 'Cobertura de testes' }
        ]
      },
      {
        userId: '002',
        name: 'Maria Santos',
        avatar: '',
        characteristics: ['Backend', 'Node.js', 'Database'],
        tasks: [
          { id: '4', titulo: 'API REST', status: 'completed', descricao: 'Endpoints principais' },
          { id: '5', titulo: 'Integração banco', status: 'in-progress', descricao: 'Configurar MongoDB' }
        ]
      },
      {
        userId: '003',
        name: 'Pedro Costa',
        avatar: 'https://via.placeholder.com/50',
        characteristics: ['DevOps', 'Docker', 'AWS'],
        tasks: []
      }
    ];
    this.filteredUsers = [...this.users];
  }

  onSearchChange(event: any) {
    this.searchTerm = event.target.value.toLowerCase();
    this.filterUsers();
  }

  onFilterChange(event: any) {
    this.filterType = event.target.value;
    this.filterUsers();
  }

  filterUsers() {
    this.filteredUsers = this.users.filter(user => {
      const matchesSearch = !this.searchTerm || 
        user.name?.toLowerCase().includes(this.searchTerm) ||
        user.userId?.toLowerCase().includes(this.searchTerm);

      const matchesFilter = !this.filterType ||
        (this.filterType === 'with-tasks' && user.tasks && user.tasks.length > 0) ||
        (this.filterType === 'without-tasks' && (!user.tasks || user.tasks.length === 0));

      return matchesSearch && matchesFilter;
    });
  }

  getInitials(name?: string): string {
    if (!name) return '?';
    return name.split(' ')
      .map(word => word.charAt(0))
      .join('')
      .toUpperCase()
      .slice(0, 2);
  }

  getStatusLabel(status: string): string {
    const labels: { [key: string]: string } = {
      'pending': 'Pendente',
      'in-progress': 'Em Progresso',
      'completed': 'Concluída'
    };
    return labels[status] || status;
  }
  getTasksByStatus(tasks: Tarefa[], status: string): string {
    const labels: { [key: string]: string } = {
      'pending': 'Pendente',
      'in-progress': 'Em Progresso',
      'completed': 'Concluída'
    };
    return labels[status] || status;
  }

  
  getActiveUsersCount(): number {
    return this.users.filter(user => user.tasks && user.tasks.length > 0).length;
  }

  onImageError(event: any) {
    event.target.style.display = 'none';
    //event.target.nextElementSibling?.style.display = 'flex';
  }

  viewUserDetails(user: Profile) {
    console.log('Ver detalhes do usuário:', user);
    // Implementar navegação para detalhes
  }

  editUser(user: Profile) {
    console.log('Editar usuário:', user);
    // Implementar edição do usuário
  }

}
