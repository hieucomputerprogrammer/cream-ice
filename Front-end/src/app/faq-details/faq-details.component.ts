import { FAQ } from './../services/models/faq.model';
import { ActivatedRoute, Router } from '@angular/router';
import { FaqDataService } from './../services/data/faq-data.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-faq-details',
  templateUrl: './faq-details.component.html',
  styleUrls: ['./faq-details.component.css']
})
export class FaqDetailsComponent implements OnInit {
  id: number;
  faq: FAQ;

  constructor(
    private faqDataService: FaqDataService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.faq = new FAQ();
    this.faqDataService.retrieveFAQ(this.id).subscribe(data => this.faq = data);
  }

  saveFAQ() {
    this.faqDataService.updateFAQ(this.id, this.faq)
      .subscribe(
        data => {
          console.log(data);
          this.router.navigate(['faqs']);
        }
      );
  }

}
