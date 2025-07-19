import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import {ReactiveFormsModule, FormBuilder, FormGroup  } from '@angular/forms';
import { Profile } from 'src/app/models/Profile';
import { ProfileService } from 'src/app/service/profile.service';

@Component({
    selector: 'app-header-component',
    templateUrl: './header-component.component.html',
    styleUrls: ['./header-component.component.css'],
    standalone: true,
    imports: [ReactiveFormsModule,CommonModule ]
})
export class HeaderComponentComponent implements OnInit{

  profile? : Profile;
  profileService: ProfileService;

  constructor(private service: ProfileService){
    this.profileService = service;
    if(this.profileService.isAuthenticate()){
      service.getProfile().subscribe(
        (profile) => {
          this.profile = profile;
          console.log(profile.name);
        },
        (error) => {
          console.log("erro ao tentar recupear profile "+error);
        }
      );
    } else {
      console.log("Usuário não autenticado, não é possível recuperar o perfil.");
    } 
  }

  logout(){
    this.profileService.logout()
  }




  ngOnInit(): void {
  }

 
}
