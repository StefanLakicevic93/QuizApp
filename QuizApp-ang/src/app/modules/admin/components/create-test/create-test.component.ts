import { Component } from '@angular/core';
import { SharedModule } from '../../../shared/shared.module';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AdminService } from '../../admin.service';
import { NzNotificationService } from 'ng-zorro-antd/notification';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-test',
  imports: [SharedModule],
  templateUrl: './create-test.component.html',
  styleUrl: './create-test.component.scss'
})
export class CreateTestComponent {

  testForm!: FormGroup;

  constructor(private fb: FormBuilder,
              private service: AdminService,
              private not: NzNotificationService,
              private router: Router
  ) {}

  ngOnInit() {
    this.testForm = this.fb.group({
      title: [null, [Validators.required]],
      description: [null, [Validators.required]],
      time: [null, [Validators.required]]
    })
  }

  submitForm() {
    if(this.testForm.valid) {
      this.service.createTest(this.testForm.value).subscribe(res => {
        this.not.success("SUCCESS", "Test created successfully", {nzDuration: 5000});
        this.router.navigateByUrl("/admin/dashboard");
      }, error => {
        this.not.error("ERROR", "Error posting test", {nzDuration: 5000});
      })
    }
  }

}
