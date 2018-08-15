import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AnthologyCardComponent } from './anthology-card.component';

describe('AnthologyCardComponent', () => {
  let component: AnthologyCardComponent;
  let fixture: ComponentFixture<AnthologyCardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AnthologyCardComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AnthologyCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
