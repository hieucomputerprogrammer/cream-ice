import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-error',
  templateUrl: './error.component.html',
  styleUrls: ['./error.component.css']
})
export class ErrorComponent implements OnInit {
  errorMessage = 'Contact Hieu Minh Le via mobile phone at +8490-810-9633 or via email at hieu.minhle@nashtechglobal.com or hieucoder@outlook.com.';

  constructor() { }

  ngOnInit(): void {
  }

}
