import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Profile } from 'src/app/models/Profile';
import { KeycloakService } from 'src/app/services/keycloak.service';
import { ProfileService } from 'src/app/services/profile.service';


@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit{

  profile?: Profile;
  userForm: FormGroup = this.fb.group({
    id: [''],
    username: [''],
    image: ['']
  }); 

  constructor(
    public keycloakService: KeycloakService,
    private profileService: ProfileService,
    private fb: FormBuilder
  ) {
    if (keycloakService.isAuthenticated()) {
      profileService.getProfile().subscribe(
        (profile) => {
          this.profile = profile;
          this.userForm = this.fb.group({
            id: [this.profile?.id],
            username: [this.profile?.username],
            image: [this.profile?.image]
          });
        },
        (erro) => {
          console.error('Erro ao carregar profile:', erro);
        }
      );
    }
  }

  ngOnInit(): void {
    this.userForm = this.fb.group({
      id: [this.profile?.id],
      username: [this.profile?.username],
      image: [this.profile?.image]
    });
  }

  onSubmit(): void {
    if (this.userForm!.valid) {
      console.log('Form Submitted:', this.userForm!.value);
    }
  }


}
