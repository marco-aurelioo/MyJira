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
    userId: [''],
    name: [''],
    avatar: ['']
  }); 

  constructor(
    public keycloakService: KeycloakService,
    private profileService: ProfileService,
    private fb: FormBuilder
  ) {
    console.log("carregando profile");
    if (keycloakService.isAuthenticated()) {
      profileService.getProfile().subscribe(
        (profile) => {
          this.profile = profile;
          this.userForm = this.fb.group({
            userId: [this.profile?.userId],
            name: [this.profile?.name],
            avatar: [this.profile?.avatar]
          });
        },
        (erro) => {
          console.error('Erro ao carregar profile:', erro);
        }
      );
    }
  }

  ngOnInit(): void {
    console.log("carregando profile onInit");
    this.userForm = this.fb.group({
      userId: [this.profile?.userId],
      name: [this.profile?.name],
      avatar: [this.profile?.avatar]
    });
  }

  onSubmit(): void {
    if (this.userForm!.valid) {
      this.profileService.salvaProfile(
        this.userForm.value.userId,
        this.userForm.value.name,
        this.userForm.value.avatar
      ).subscribe(
        (profile) => {
          this.profile = profile;
        },
        (erro) => {
          console.error('Erro ao carregar profile:', erro);
        }
      )
    }
  }


}
