import { Component } from '@angular/core';
import { TasksService } from '../services/tasks.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {


  constructor(private taskService: TasksService){
    this.autenticateToken = taskService.token; 
  }

  autenticateToken: string | undefined; 

}
