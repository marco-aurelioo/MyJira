import { CommonModule } from '@angular/common';
import { Component, NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrl: './user-profile.component.css'
})
export class UserProfileComponent {
  user = {
    name: 'John Doe',
    nickname: 'jdoe',
    position: 'Software Engineer',
    experiences: [
      'Developed a full-stack web application',
      'Worked with Agile methodologies',
      'Implemented CI/CD pipelines'
    ],
    currentProjects: [
      { value: 'project-aka1', label: 'Project A' },
      { value: 'project-aka2', label: 'Project B' }
    ],
    skills: ['JavaScript', 'Angular', 'Node.js', 'CSS'],
    photo: './assets/imgs/usuarios/image.png',
    icon: './assets/imgs/icons/image.png',
    teams: ['Team Alpha', 'Team Beta']
  };
}
