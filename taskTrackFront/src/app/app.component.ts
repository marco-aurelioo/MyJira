import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { HeaderComponent } from './component/header/header.component';
import { FooterComponent } from './component/footer/footer.component';
import { SideBarComponent } from './component/side-bar/side-bar.component';
import { NewTaskComponent } from './component/new-task/new-task.component';
import { FormsModule, NgForm } from '@angular/forms';
import { ScrumBoardComponent } from './component/scrum-board/scrum-board.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    RouterOutlet,
    HeaderComponent, 
    FooterComponent,
    SideBarComponent,
    NewTaskComponent,
    ScrumBoardComponent,
    FormsModule
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'taskTrackFront';
}
