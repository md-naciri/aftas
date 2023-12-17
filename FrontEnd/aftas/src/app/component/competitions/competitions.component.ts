import { Component } from '@angular/core';
import { Competition } from 'src/app/entity/competition';
import { CompetitionResponse } from 'src/app/entity/competition-response';
import { CompetitionService } from 'src/app/service/competition.service';

@Component({
  selector: 'app-competitions',
  templateUrl: './competitions.component.html',
  styleUrls: ['./competitions.component.css']
})
export class CompetitionsComponent {
  competitions: Competition[]=[];
  name: string = "ga3 Satat bnat l97ab"
  constructor(private competitionService: CompetitionService){}
  ngOnInit(): void{
    this.getCompetions()
  }
  getCompetions(){
    this.competitionService.getCompetitions().subscribe((competition: CompetitionResponse) => {
      this.competitions = competition.data
      console.log(this.competitions)
    })
  }
}
