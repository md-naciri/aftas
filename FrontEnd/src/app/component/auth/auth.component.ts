import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormControl  } from '@angular/forms';
import { Route, Router } from '@angular/router';
import { UserRegister } from 'src/app/entity/user-register';
import { AuthService } from 'src/app/service/auth.service';
import { JwtTokenService } from 'src/app/service/jwt-token.service';
@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.css']
})
export class AuthComponent {
  user!: UserRegister;
  
  RegisterForm: FormGroup;


  constructor(
    private service: AuthService,
    private fb: FormBuilder,
    private tokenService : JwtTokenService,
    private router : Router
    ){
      this.RegisterForm = fb.group({
        name: new FormControl(''),
        username: new FormControl(''),
        password: new FormControl(''),
      })
    }


    onSubmite() {
      if (this.RegisterForm.valid) {
        const formData = this.RegisterForm.value;
        console.log(formData);
    
        this.service.login(formData).subscribe({
          next: (val: any) => {
            console.log(val.token);
            this.tokenService.saveToken(val.token)
            this.router.navigate(['/competitions'])
          },
          error: (err: any) => {
            console.log(err);
          }
        });
      }
    }
    
    

}
