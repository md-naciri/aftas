import { Component } from '@angular/core';
import { Competition } from 'src/app/entity/competition';
import { CompetitionResponse } from 'src/app/entity/competition-response';
import { CompetitionService } from 'src/app/service/competition.service';
import { FormBuilder, FormGroup, FormControl  } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { ViewChild, ElementRef } from '@angular/core';
import { HuntingService } from 'src/app/service/hunting.service';
import { MemberService } from 'src/app/service/member.service';
import { MemberResponse } from 'src/app/entity/member-response';
import { Member } from 'src/app/entity/member';
import { RankingService } from 'src/app/service/ranking.service';
import { PageEvent } from '@angular/material/paginator';
import { filter } from 'rxjs';



@Component({
  selector: 'app-competitions',
  templateUrl: './competitions.component.html',
  styleUrls: ['./competitions.component.css']
})
export class CompetitionsComponent {
  myForm: FormGroup;

  competitions: Competition[]=[];
  filterType: string = "";
  addCompetitionForm: FormGroup;
  huntingForm: FormGroup;
  members: Member[] = [];

  pageSizeOptions: number[] = [5, 10, 20];
  pageSize: number = 5;
  pageIndex: number = 0;
  totalCompetitions: number = 0;

  competitionCode: string = "";
  @ViewChild('closeModalButton', { static: false }) closeModalButton: ElementRef | undefined;
  @ViewChild('closeModalButton2', { static: false }) closeModalButton2: ElementRef | undefined;

  constructor(
    private competitionService: CompetitionService,
    private fb: FormBuilder,
    private toastr: ToastrService,
    private huntingService: HuntingService,
    private memberService: MemberService,
    ){
    this.addCompetitionForm = this.fb.group({
      date: "",
      startTime: "",
      endTime: "",
      location: "",
      amount: ""
    })
    this.huntingForm = this.fb.group({
      memberNumber: "",
      competitionCode:"",
      fishName:"",
      fishWeight:""
    })
    this.myForm = new FormGroup({
      // form controls
    });
  }
  ngOnInit(): void{
    this.getCompetionsPagination()
    this.getMembers()
  }
  getCompetionsPagination(){
    this.competitionService.getCompetitionsPagination(this.pageIndex, this.pageSize).subscribe((competition: CompetitionResponse) => {
      this.competitions = competition.data
      this.totalCompetitions = competition.data.length
    })
  }
  getCompetions(){
    this.competitionService.getCompetitions().subscribe((competition: CompetitionResponse) => {
      this.competitions = competition.data
    })
  }
  filterCompetitions(){
    this.competitionService.filterCompetitions(this.filterType).subscribe((competition: CompetitionResponse) => {
      this.competitions = competition.data
    })
  }
  createCompetition(){
    if (this.addCompetitionForm.valid) {
      
      this.competitionService.createCompetition(this.addCompetitionForm.value).subscribe({
        next: (val: any) => {
          this.toastr.success('Competition created successfully.', 'Success', {
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
  onPageChange(event: PageEvent) {
    this.pageIndex = event.pageIndex;
    this.pageSize = event.pageSize;
    this.getCompetionsPagination();
  }
  insertHunt(){    
    this.huntingForm.get('competitionCode')?.setValue(this.competitionCode)
    console.log(this.huntingForm.value);
    if (this.huntingForm.valid) {
      this.huntingService.instertHunt(this.huntingForm.value).subscribe({
        next: (val: any) => {
          this.toastr.success('Hunting inserted successfully', 'Success', {
            closeButton: true,
            timeOut: 3000,
          });
          this.closeModalTrigger2();
          //this.getMembers();
          this.huntingForm.reset();
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

  getMembers(){
    this.memberService.getMembers().subscribe(
      (member: MemberResponse) => {this.members = member.data}
    )
  }
  closeModalTrigger(){
    if (this.closeModalButton) {
      this.closeModalButton.nativeElement.click();
    }
  }
  closeModalTrigger2(){
    if (this.closeModalButton2) {
      this.closeModalButton2.nativeElement.click();
    }
  }
  saveCompetitionCode(code: string){
    this.competitionCode = code;
  }
}
