import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TelemetriaFormComponent } from './telemetria-form.component';

describe('TelemetriaFormComponent', () => {
  let component: TelemetriaFormComponent;
  let fixture: ComponentFixture<TelemetriaFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TelemetriaFormComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TelemetriaFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
