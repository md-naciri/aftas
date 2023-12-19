import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CompetitionsComponent } from './component/competitions/competitions.component';
import { MembersComponent } from './component/members/members.component';
import { RankingsComponent } from './component/rankings/rankings.component';

const routes: Routes = [
  {
    path: "competitions",
    component: CompetitionsComponent
  },
  {
    path: "members",
    component: MembersComponent
  },
  {
    path: "ranking",
    component: RankingsComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
