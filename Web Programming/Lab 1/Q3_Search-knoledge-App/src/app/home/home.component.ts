import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {HttpClient} from '@angular/common/http';
import { DisplayRecipeDetailsComponent} from '../dispaly-recipe-details/display-recipe-details.component';
import { ControlMessagesComponent} from '../control-messages/control-messages.component';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  public response: any;
  searchQuery: FormGroup;


  constructor( private http: HttpClient, private fb: FormBuilder) { }



  ngOnInit() {
    this.searchQuery = this.fb.group({
      Query: ['Bill Gates', [Validators.required]]
    });

    this.getResponse();
  }

  getResponse(): void {

    this.http.get('https://kgsearch.googleapis.com/v1/entities:search?query=' + this.searchQuery.controls.Query.value + '&key=AIzaSyDvEDNg-1pDe7Y4lNjKG-V-r8Fm12n1quA').subscribe(data => {
      this.response = data;
      console.log(data);
    });

    // responsiveVoice.speak(this.searchQuery.controls.Query.value );
  }
}
