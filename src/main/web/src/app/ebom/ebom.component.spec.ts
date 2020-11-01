import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';

import { EbomComponent } from './ebom.component';

describe('EbomComponent', () => {
  let component: EbomComponent;
  let fixture: ComponentFixture<EbomComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ EbomComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EbomComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
