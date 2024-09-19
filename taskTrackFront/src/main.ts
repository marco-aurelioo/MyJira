import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';
import { AppModule } from './app/app.module';

platformBrowserDynamic()
  .bootstrapModule(AppModule)
  .then(appRef => {
    console.log('Módulo Angular inicializado com sucesso');
  })
  .catch(err => {
    console.error('Erro ao inicializar o módulo Angular:', err);
  });

// Adicionando logs para verificar o status de inicialização
console.log('Iniciando aplicação Angular com Keycloak');
