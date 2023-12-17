import { Component } from '@angular/core';
import { Competition } from 'src/app/entity/competition';
import { CompetitionResponse } from 'src/app/entity/competition-response';
import { CompetitionService } from 'src/app/service/competition.service';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { ViewChild, ElementRef } from '@angular/core';



@Component({
  selector: 'app-competitions',
  templateUrl: './competitions.component.html',
  styleUrls: ['./competitions.component.css']
})
export class CompetitionsComponent {
  competitions: Competition[]=[];
  addCompetitionForm: FormGroup;
  @ViewChild('closeModalButton', { static: false }) closeModalButton: ElementRef | undefined;
  constructor(
    private competitionService: CompetitionService,
    private fb: FormBuilder,
    private toastr: ToastrService
    ){
    this.addCompetitionForm = this.fb.group({
      date: "",
      startTime: "",
      endTime: "",
      location: "",
      amount: ""
    })
  }
  ngOnInit(): void{
    this.getCompetions()
  }
  getCompetions(){
    this.competitionService.getCompetitions().subscribe((competition: CompetitionResponse) => {
      this.competitions = competition.data
      
    })
  }
  createCompetition(){
    if (this.addCompetitionForm.valid) {
      this.competitionService.createCompetition(this.addCompetitionForm.value).subscribe({
        next: (val: any) => {
          this.toastr.success('Item moved successfully.', 'Success', {
            closeButton: true,
            timeOut: 3000,
          });
          this.closeModalTrigger();
          this.getCompetions();
          this.addCompetitionForm.reset();
        },
        error: (err: any) => {
          console.log(err);
            this.toastr.error(err.error.error,"error",{
              closeButton: true,
              timeOut: 3000,
            });
        }
      });
    }
  }
  closeModalTrigger(){
    if (this.closeModalButton) {
      this.closeModalButton.nativeElement.click();
    }
  }
}
