import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';

import { PartCrudComponent } from './part-crud.component';

describe('PartCrudComponent', () => {
  let component: PartCrudComponent;
  let fixture: ComponentFixture<PartCrudComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ PartCrudComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PartCrudComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
