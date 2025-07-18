import { bootstrapApplication } from '@angular/platform-browser';
import { AppComponent } from './app/app.component';
import { KeycloakService } from 'keycloak-angular';
import { keycloakConfig, keycloakInitOptions } from './app/config/keycloak.config';
import { importProvidersFrom, APP_INITIALIZER } from '@angular/core';
import { KeycloakAngularModule } from 'keycloak-angular';
import { provideHttpClient, withInterceptors } from '@angular/common/http';
import { provideRouter } from '@angular/router';
import { routes } from './app/app.routes';
import { keycloakInterceptor } from './app/interceptors/auth.interceptor';


// Função para inicializar o Keycloak
function initializeKeycloak(keycloak: KeycloakService) {
  return () => {
    console.log('Iniciando Keycloak...');
    
    return keycloak.init({
      config: keycloakConfig,
      initOptions: {
        onLoad: 'check-sso' as const,
        checkLoginIframe: false, // IMPORTANTE: Desabilita o iframe que causa o erro
        silentCheckSsoRedirectUri: window.location.origin + '/assets/silent-check-sso.html',
        pkceMethod: 'S256', // Adiciona PKCE para segurança
        flow: 'standard' as const
      }
    }).then((authenticated) => {
      console.log('Keycloak inicializado:', authenticated ? 'Usuário autenticado' : 'Usuário não autenticado');
      return authenticated;
    }).catch((error) => {
      console.error('Erro ao inicializar Keycloak:', error);
      // Em caso de erro, continue sem bloquear a aplicação
      return false;
    });
  };
}

bootstrapApplication(AppComponent, {
  providers: [
    provideRouter(routes),
    provideHttpClient(),
    importProvidersFrom(KeycloakAngularModule),
    {
      provide: APP_INITIALIZER,
      useFactory: initializeKeycloak,
      multi: true,
      deps: [KeycloakService]
    }
  ]
}).catch(err => {
  console.error('Erro ao iniciar aplicação:', err);
});