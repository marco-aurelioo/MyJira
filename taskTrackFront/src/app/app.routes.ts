import { Routes } from '@angular/router';
import { NewTaskComponent } from './component/new-task/new-task.component';
import { ScrumBoardComponent } from './component/scrum-board/scrum-board.component';
import { UserProfileComponent } from './component/user-profile/user-profile.component';

export const routes: Routes = [
{path:"new-task", component:NewTaskComponent },
{path:"scrum-board", component:ScrumBoardComponent },
{path:"user-profile", component:UserProfileComponent }

];
