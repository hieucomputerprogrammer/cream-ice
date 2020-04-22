import { IceCream } from './../services/models/ice-cream.model';
import { ActivatedRoute, Router } from '@angular/router';
import { IceCreamDataService } from './../services/data/ice-cream-data.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-ice-cream-create',
  templateUrl: './ice-cream-create.component.html',
  styleUrls: ['./ice-cream-create.component.css']
})
export class IceCreamCreateComponent implements OnInit {
  id: number;
  iceCream: IceCream;

  constructor(
    private iceCreamDataService: IceCreamDataService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.iceCream = new IceCream();
  }

  addIceCream() {
    this.iceCreamDataService.createIceCream(this.iceCream)
    .subscribe(
      data => {
        console.log(data);
        this.router.navigate(['ice-creams']);
      }
    );
  }

}
