import { IceCream } from './../services/models/ice-cream.model';
import { ActivatedRoute, Router } from '@angular/router';
import { IceCreamDataService } from './../services/data/ice-cream-data.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-ice-cream-details',
  templateUrl: './ice-cream-details.component.html',
  styleUrls: ['./ice-cream-details.component.css']
})
export class IceCreamDetailsComponent implements OnInit {
  id: number;
  iceCream: IceCream;

  constructor(
    private iceCreamDataService: IceCreamDataService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.iceCream = new IceCream();
    this.iceCreamDataService.retrieveIceCream(this.id).subscribe(data => this.iceCream = data);
  }

  saveIceCream() {
    this.iceCreamDataService.updateIceCream(this.id, this.iceCream)
      .subscribe(
        data => {
          console.log(data);
          this.router.navigate(['ice-creams']);
        }
      );
  }

}
