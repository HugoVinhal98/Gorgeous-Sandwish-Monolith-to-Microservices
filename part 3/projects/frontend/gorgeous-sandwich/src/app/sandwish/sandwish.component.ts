import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-sandwish',
  templateUrl: './sandwish.component.html',
  styleUrls: ['./sandwish.component.scss']
})
export class SandwishComponent implements OnInit {

  constructor() { }
  @Input() commentTitle: string;
  @Input() reviewTitle: string;
  @Input() sande: any;
  
  ngOnInit() {
  }

}
