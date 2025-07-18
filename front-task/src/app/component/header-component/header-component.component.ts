import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Profile } from 'src/app/models/Profile';
import { ProfileService } from 'src/app/service/profile.service';

@Component({
    selector: 'app-header-component',
    templateUrl: './header-component.component.html',
    styleUrls: ['./header-component.component.css'],
    standalone: true,
    imports: [CommonModule]
})
export class HeaderComponentComponent implements OnInit{

  profile? : Profile;
  profileService: ProfileService;

  constructor(private service: ProfileService){
    this.profileService = service;
    
    service.getProfile().subscribe(
      (profile) => {
        this.profile = profile;
        console.log(profile.name);
      },
      (error) => {
        console.log("erro ao tentar recupear profile "+error);
      }
    );
  }

  logout(){
    this.profileService.logout()
  }




  ngOnInit(): void {
  }

 
}
