import { FAQ } from './../services/models/faq.model';
import { ActivatedRoute, Router } from '@angular/router';
import { FaqDataService } from './../services/data/faq-data.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-faq-create',
  templateUrl: './faq-create.component.html',
  styleUrls: ['./faq-create.component.css']
})
export class FaqCreateComponent implements OnInit {
  id: number;
  faq: FAQ;

  constructor(
    private faqDataService: FaqDataService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.faq = new FAQ();
  }

  addFAQ() {
    this.faqDataService.createFAQ(this.faq)
    .subscribe(
      data => {
        console.log(data);
        this.router.navigate(['faqs']);
      }
    );
  }

}
