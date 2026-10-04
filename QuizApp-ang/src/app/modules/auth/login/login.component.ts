import { Component } from '@angular/core';
import { SharedModule } from '../../shared/shared.module';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../auth.service';
import { NzMessageService } from 'ng-zorro-antd/message';
import { Router } from '@angular/router';
import { UserStorageService } from '../user-storage.service';

@Component({
  selector: 'app-login',
  imports: [SharedModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {

  loginForm!: FormGroup;

  constructor(private fb: FormBuilder,
              private service: AuthService,
              private msg: NzMessageService,
              private router: Router
  ) {}

  ngOnInit() {
    this.loginForm = this.fb.group({
      email: [null, [Validators.required]],
      password: [null, [Validators.required]]
    })
  }

  submitForm() {
    this.service.login(this.loginForm.value).subscribe(res => {
      this.msg.success("Login successful", {nzDuration: 5000});
      const user = {
        id: res.id,
        role: res.role
      }
      UserStorageService.saveUser(user);

      if(UserStorageService.isAdminLoggedIn()) {
        this.router.navigateByUrl("/admin/dashboard");
      } else if(UserStorageService.isUserLoggedIn()) {
        this.router.navigateByUrl("/user/dashboard");
      }
      console.log(res);
    }, error => {
      this.msg.error("Wrong credentials", {nzDuration: 5000});
    })
  }

}
