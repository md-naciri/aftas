import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CompetitionsComponent } from './component/competitions/competitions.component';
import { MembersComponent } from './component/members/members.component';
import { RankingsComponent } from './component/rankings/rankings.component';
import { HomeComponent } from './component/home/home.component';
import { AuthComponent } from './component/auth/auth.component';
import { LoginComponent } from './component/login/login.component';
import { isLoggedGuard } from './guard/is-logged.guard';
import { isAlerdyLoginGuard } from './guard/is-alerdy-login.guard';

const routes: Routes = [
  {
    path: '',
    component: HomeComponent
  },
  {
    path: "competitions",
    component: CompetitionsComponent,
    canActivate: [isLoggedGuard]  
  },
  {
    path: "members",
    component: MembersComponent,
    canActivate: [isLoggedGuard]  
  },
  {
    path: "ranking",
    component: RankingsComponent,
    canActivate: [isLoggedGuard]  
  },
  {
    path: "auth",
    component: AuthComponent,
    canActivate: [isAlerdyLoginGuard]
  },
  {
    path: "login",
    component: LoginComponent,
    canActivate: [isAlerdyLoginGuard]

  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
