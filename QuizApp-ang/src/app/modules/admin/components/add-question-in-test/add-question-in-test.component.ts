import { Component } from '@angular/core';
import { SharedModule } from '../../../shared/shared.module';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AdminService } from '../../admin.service';
import { ActivatedRoute, Router } from '@angular/router';
import { NzNotificationService } from 'ng-zorro-antd/notification';

@Component({
  selector: 'app-add-question-in-test',
  imports: [SharedModule],
  templateUrl: './add-question-in-test.component.html',
  styleUrl: './add-question-in-test.component.scss'
})
export class AddQuestionInTestComponent {

  id: number | null;
  questionForm!: FormGroup;

  constructor(private fb: FormBuilder,
              private service: AdminService,
              private route: ActivatedRoute,
              private router: Router,
              private not: NzNotificationService
  ) {}

  ngOnInit() {
    this.id = this.route.snapshot.params["id"];
    this.questionForm = this.fb.group({
      questionText: [null, [Validators.required]],
      optionA: [null, [Validators.required]],
      optionB: [null, [Validators.required]],
      optionC: [null, [Validators.required]],
      optionD: [null, [Validators.required]],
      correctOption: [null, [Validators.required]],
    })
  }

  submitForm() {
    const questionDto = this.questionForm.value;
    questionDto.id = this.id;
    this.service.addQuestionInTest(questionDto).subscribe(res => {
      this.not.success("SUCCESS", "Question posted successfully", {nzDuration: 5000});
      this.router.navigateByUrl("/admin/dashboard");
    }, error => {
      this.not.error("ERROR", "Something went wrong", {nzDuration: 5000});
    })
  }


}
