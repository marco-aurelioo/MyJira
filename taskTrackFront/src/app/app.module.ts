import { APP_INITIALIZER, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { HeaderComponent } from './component/header/header.component';
import { FooterComponent } from './component/footer/footer.component';
import { SideBarComponent } from './component/side-bar/side-bar.component';
import { NewTaskComponent } from './component/new-task/new-task.component';
import { ScrumBoardComponent } from './component/scrum-board/scrum-board.component';
import { UserProfileComponent } from './component/user-profile/user-profile.component';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { KeycloakAngularModule, KeycloakService } from 'keycloak-angular';
import { routes } from './app.routes';


function initializeKeycloak(keycloak: KeycloakService) {
  return () =>
    keycloak.init({
      config: {
        url: 'http://localhost:9090/',
        realm: 'dev',
        clientId: 'task-track'
      },
      initOptions: {
        onLoad: 'login-required',
        checkLoginIframe: false,
        pkceMethod: 'S256'
      }
    })
    .then(authenticated => {
      console.log('Keycloak inicializado com sucesso');
      console.log('Autenticado:', authenticated);
    })
    .catch(error => {
      console.error('Erro ao inicializar Keycloak:', error);
      return Promise.reject(error);
    });
}

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
    KeycloakAngularModule,
    BrowserModule,
    CommonModule,
    FormsModule,
    RouterModule.forRoot(routes)
  ],
  providers: [
    {
      provide: APP_INITIALIZER,
      useFactory: initializeKeycloak,
      multi: true,
      deps: [KeycloakService]
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
