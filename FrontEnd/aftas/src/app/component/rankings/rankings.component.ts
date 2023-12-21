import { Component } from '@angular/core';
import { Ranking } from 'src/app/entity/ranking';
import { RankingResponse } from 'src/app/entity/ranking-response';
import { ViewChild, ElementRef } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { MemberService } from 'src/app/service/member.service';
import { RankingService } from 'src/app/service/ranking.service';
import { CompetitionService } from 'src/app/service/competition.service';
import { Competition } from 'src/app/entity/competition';
import { CompetitionResponse } from 'src/app/entity/competition-response';

@Component({
  selector: 'app-rankings',
  templateUrl: './rankings.component.html',
  styleUrls: ['./rankings.component.css']
})
export class RankingsComponent {
  competitions: Competition[]=[];
  podium: Ranking[]=[];
  competitionCode: string = "";
  @ViewChild('closeModalButton', { static: false }) closeModalButton: ElementRef | undefined;
  constructor(
    private rankingService: RankingService,
    private fb: FormBuilder,
    private toastr: ToastrService,
    private competitionService : CompetitionService
  ){
    
  }
  ngOnInit(): void{
    this.getCompetions()
  }
  getCompetions(){
    this.competitionService.getCompetitions().subscribe((competition: CompetitionResponse) => {
      this.competitions = competition.data
    })
  }
  getPodium(){    
    console.log("dekhlat");
    this.rankingService.getMembersInCompetition(this.competitionCode).subscribe((ranking: RankingResponse) => {
      this.podium = ranking.data
    })
    console.log(this.podium);
    
  }
  saveCompetitionCode(code: string){
    this.competitionCode = code;

    setTimeout(() => {
    this.getPodium();
  }, 500);
  }

}
