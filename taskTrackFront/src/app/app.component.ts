import { Component, NgModule, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { HeaderComponent } from './component/header/header.component';
import { FooterComponent } from './component/footer/footer.component';
import { SideBarComponent } from './component/side-bar/side-bar.component';
import { NewTaskComponent } from './component/new-task/new-task.component';
import { FormsModule, NgForm } from '@angular/forms';
import { ScrumBoardComponent } from './component/scrum-board/scrum-board.component';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    CommonModule,
    RouterOutlet,
    HeaderComponent, 
    FooterComponent,
    SideBarComponent,
    NewTaskComponent,
    ScrumBoardComponent,
    FormsModule
  ],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent  {
  title = "TaskTrack"
}


