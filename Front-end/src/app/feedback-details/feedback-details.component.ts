import { Feedback } from './../services/models/feedback.model';
import { ActivatedRoute, Router } from '@angular/router';
import { FeedbackDataService } from './../services/data/feedback-data.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-feedback-details',
  templateUrl: './feedback-details.component.html',
  styleUrls: ['./feedback-details.component.css']
})
export class FeedbackDetailsComponent implements OnInit {
  id: number;
  feedback: Feedback;

  constructor(
    private feedbackDataService: FeedbackDataService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.feedback = new Feedback();
    this.feedbackDataService.retrieveFeedback(this.id).subscribe(data => this.feedback = data);
  }

  saveFeedback() {
    this.feedbackDataService.updateFeedback(this.id, this.feedback)
      .subscribe(
        data => {
          console.log(data);
          this.router.navigate(['feedbacks']);
        }
      );
  }

}
