import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Profile } from 'src/app/models/Profile';
import { KeycloakService } from 'src/app/services/keycloak.service';
import { ProfileService } from 'src/app/services/profile.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit{

  profile?: Profile;

  constructor(
    private router: Router,
    public keycloakService: KeycloakService, 
    private profileService: ProfileService) {

    if(keycloakService.isAuthenticated()){
       profileService.getProfile().subscribe(
        (profile) => {
          this.profile = profile;
        },
        (erro) => {
          console.error('Erro ao carregar profile:', erro);
        }
       );
    }else{
      this.router.navigate(['/login'])
    }

  }

  ngOnInit(): void {
  }



}
