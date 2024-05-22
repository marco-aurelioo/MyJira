import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-scrum-board',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './scrum-board.component.html',
  styleUrl: './scrum-board.component.css'
})
export class ScrumBoardComponent {
  columns = [
    { title: 'Back-log', icon: 'fas fa-clipboard-list', tasks: [{ title: 'Task 1', days: 5, typeIcon: 'fas fa-bug' }] },
    { title: 'Todo', icon: 'fas fa-tasks', tasks: [{ title: 'Task 2', days: 3, typeIcon: 'fas fa-code' }] },
    { title: 'Doing', icon: 'fas fa-spinner', tasks: [{ title: 'Task 3', days: 1, typeIcon: 'fas fa-wrench' }] },
    { title: 'Test', icon: 'fas fa-vial', tasks: [{ title: 'Task 4', days: 2, typeIcon: 'fas fa-vial' }] },
    { title: 'Review', icon: 'fas fa-search', tasks: [{ title: 'Task 5', days: 4, typeIcon: 'fas fa-search' }] },
    { title: 'Done', icon: 'fas fa-check', tasks: [{ title: 'Task 6', days: 0, typeIcon: 'fas fa-check' }] },
    { title: 'Prod', icon: 'fas fa-rocket', tasks: [{ title: 'Task 7', days: 0, typeIcon: 'fas fa-rocket' }] }
  ];
}
