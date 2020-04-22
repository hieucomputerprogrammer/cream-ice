import { Feedback } from './../services/models/feedback.model';
import { ActivatedRoute, Router } from '@angular/router';
import { FeedbackDataService } from './../services/data/feedback-data.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-feedback-create',
  templateUrl: './feedback-create.component.html',
  styleUrls: ['./feedback-create.component.css']
})
export class FeedbackCreateComponent implements OnInit {
  id: number;
  feedback: Feedback;

  constructor(
    private feedbackDataService: FeedbackDataService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.feedback = new Feedback();
  }

  addFeedback() {
    this.feedbackDataService.createFeedback(this.feedback)
    .subscribe(
      data => {
        console.log(data);
        this.router.navigate(['feedbacks']);
      }
    );
  }

}
