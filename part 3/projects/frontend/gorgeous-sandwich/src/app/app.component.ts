import { Component } from '@angular/core';
import { loc } from '../locale/labels'
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  public title = 'Skedit-Angular';
  public classApplied = false;
  public isLogged = false;
  public labels = loc.pt;
  public isPt = true;

  public listaDeSandes = undefined;

  constructor(public http: HttpClient) { }

  ngOnInit() {
    // gets user by its id if is loged in
    this.http.get('http://localhost:7002/detailedSandwishes').subscribe(resp => {
      this.listaDeSandes = resp["collection"];
    });

  }

  public changeLanguage() {
    this.classApplied = !this.classApplied;
    this.isPt = !this.isPt;
    this.labels = this.isPt ? loc.pt : loc.en;
  }
}
