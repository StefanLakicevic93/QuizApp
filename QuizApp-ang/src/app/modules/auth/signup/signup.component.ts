import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { SharedModule } from './../../shared/shared.module';
import { Component } from '@angular/core';
import { NzMessageService } from 'ng-zorro-antd/message';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-signup',
  imports: [SharedModule],
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.scss'
})
export class SignupComponent {

  signupForm!: FormGroup;

  constructor(private fb: FormBuilder,
              private msg: NzMessageService,
              private router: Router,
              private service: AuthService
  ) {}

  ngOnInit() {
    this.signupForm = this.fb.group({
      name: [null, [Validators.required]],
      email: [null, [Validators.required, Validators.email]],
      password: [null, [Validators.required]]
    })
  }

  submitForm() {
    this.service.signup(this.signupForm.value).subscribe(res => {
      this.msg.success("Signup successful", {nzDuration: 5000});
      this.router.navigateByUrl("/login");
    }, error => {
      this.msg.error("Something went wrong", {nzDuration: 5000});
    })
  }

}
