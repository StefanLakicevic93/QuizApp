import { Component } from '@angular/core';
import { SharedModule } from '../../../shared/shared.module';
import { AdminService } from '../../admin.service';

@Component({
  selector: 'app-view-test-results',
  imports: [SharedModule],
  templateUrl: './view-test-results.component.html',
  styleUrl: './view-test-results.component.scss'
})
export class ViewTestResultsComponent {

  resultData: any;

  constructor(private service: AdminService) {}

  ngOnInit() {
    this.getTestResults();
  }

  getTestResults() {
    this.service.getTestResults().subscribe(res => {
      this.resultData = res;
      console.log(this.resultData);
    })
  }

}
