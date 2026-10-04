import { Component } from '@angular/core';
import { SharedModule } from '../../../shared/shared.module';
import { NzNotificationService } from 'ng-zorro-antd/notification';
import { AdminService } from '../../admin.service';

@Component({
  selector: 'app-dashboard',
  imports: [SharedModule],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.scss'
})
export class DashboardComponent {

  tests = [];

  constructor(private not: NzNotificationService,
              private service: AdminService
  ) {}

  ngOnInit() {
    this.getAllTests();
  }

  getAllTests() {
    this.service.getAllTests().subscribe(res => {
      this.tests = res;
    }, error => {
      this.not.error("ERROR", "Something went wrong", {nzDuration: 5000});
    })
  }

  getFormattedTime(time): string {
    const minutes = Math.floor(time / 60);
    const seconds = time % 60;
    return `${minutes} minutes ${seconds} seconds`;

  }

}
