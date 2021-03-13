/* tslint:disable:no-unused-variable */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { DebugElement } from '@angular/core';

import { SandwishComponent } from './sandwish.component';

describe('SandwishComponent', () => {
  let component: SandwishComponent;
  let fixture: ComponentFixture<SandwishComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SandwishComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SandwishComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
