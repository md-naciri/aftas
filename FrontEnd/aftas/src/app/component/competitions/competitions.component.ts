import { Component } from '@angular/core';
import { CompetitionService } from 'src/app/service/competition.service';

@Component({
  selector: 'app-competitions',
  templateUrl: './competitions.component.html',
  styleUrls: ['./competitions.component.css']
})
export class CompetitionsComponent {
  repetition: number[] = [1, 2, 3, 5, 4, 6, 7, 8, 9, 10]
  constructor(private competitionService: CompetitionService){}
  ngOnInit(): void{
    this.getCompetions()
  }
  getCompetions(){
    this.competitionService.getCompetitions().subscribe(data => {
      console.log(data);
    })
  }
}
