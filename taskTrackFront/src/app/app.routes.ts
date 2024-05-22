import { Routes } from '@angular/router';
import { NewTaskComponent } from './component/new-task/new-task.component';
import { ScrumBoardComponent } from './component/scrum-board/scrum-board.component';

export const routes: Routes = [
{path:"new-task", component:NewTaskComponent },
{path:"scrum-board", component:ScrumBoardComponent }

];
