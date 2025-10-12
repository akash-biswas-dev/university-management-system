import { Routes } from '@angular/router';
import {HomePage} from '../pages/home/home.page';
import {AuthPage} from '../pages/auth/auth.page';

export const routes: Routes = [
  {
    path: '',
    component: HomePage
  },
  {
    path: 'auth',
    component: AuthPage
  }
];
