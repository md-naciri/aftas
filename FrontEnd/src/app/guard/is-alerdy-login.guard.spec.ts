import { TestBed } from '@angular/core/testing';
import { CanActivateFn } from '@angular/router';

import { isAlerdyLoginGuard } from './is-alerdy-login.guard';

describe('isAlerdyLoginGuard', () => {
  const executeGuard: CanActivateFn = (...guardParameters) => 
      TestBed.runInInjectionContext(() => isAlerdyLoginGuard(...guardParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(executeGuard).toBeTruthy();
  });
});
