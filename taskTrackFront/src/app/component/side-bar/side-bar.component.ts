import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-side-bar',
  templateUrl: './side-bar.component.html',
  styleUrl: './side-bar.component.css'
})
export class SideBarComponent implements OnInit {

  isSidebarActive: boolean = false; // Variável para controlar o estado do sidebar

  constructor() { }

  ngOnInit(): void {
  }

  toggleSidebar(): void {
    const sidebar = document.querySelector('.sidebar');
    sidebar?.classList.toggle('active'); // Adiciona ou remove a classe 'active' do sidebar
    this.isSidebarActive = !this.isSidebarActive; // Alterna o estado do sidebar
  }
}
