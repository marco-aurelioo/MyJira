import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { HeaderComponent } from './component/header/header.component';
import { FooterComponent } from './component/footer/footer.component';
import { SideBarComponent } from './component/side-bar/side-bar.component';
import { NewTaskComponent } from './component/new-task/new-task.component';
import { ScrumBoardComponent } from './component/scrum-board/scrum-board.component';
import { UserProfileComponent } from './component/user-profile/user-profile.component';
import { RouterModule } from '@angular/router';
import { routes } from './app.routes';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    NewTaskComponent,
    ScrumBoardComponent,
    UserProfileComponent,
    SideBarComponent
  ],
  imports: [
    BrowserModule,
    CommonModule,
    FormsModule,
    RouterModule.forRoot(routes)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
