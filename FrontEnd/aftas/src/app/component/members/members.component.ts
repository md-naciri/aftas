import { Component } from '@angular/core';
import { Member } from 'src/app/entity/member';
import { MemberResponse } from 'src/app/entity/member-response';
import { ViewChild, ElementRef } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { MemberService } from 'src/app/service/member.service';
import { RankingService } from 'src/app/service/ranking.service';
import { CompetitionService } from 'src/app/service/competition.service';
import { Competition } from 'src/app/entity/competition';
import { CompetitionResponse } from 'src/app/entity/competition-response';
import { initFlowbite } from 'flowbite';


@Component({
  selector: 'app-members',
  templateUrl: './members.component.html',
  styleUrls: ['./members.component.css']
})
export class MembersComponent {
  members: Member[]=[];
  assignMemberForm: FormGroup;
  addMemberForm: FormGroup;
  memberNumber: number = 0;
  competitionsFromOneDayNow: Competition[]=[];
  search: string = ""
  
  @ViewChild('closeModalButton', { static: false }) closeModalButton: ElementRef | undefined;
  @ViewChild('closeModalButton2', { static: false }) closeModalButton2: ElementRef | undefined;

  constructor(
    private ranking: RankingService,
    private memberService: MemberService,
    private fb: FormBuilder,
    private toastr: ToastrService,
    private competitionService: CompetitionService,

  ){
    this.addMemberForm = this.fb.group({
      firstName:"",
      lastName:"",
      accessionDate:"",
      nationality:"",
      identityDocument:"",
      identityNumber:""
    })
    this.assignMemberForm = this.fb.group({
      member_number: 0,
      competition_code: ""
      })
  }
  ngOnInit(): void{
    this.getMembers();
    this.getCompetionsAfterToday();
    initFlowbite();
  }
  getMembers(){
  
    this.memberService.getMembers().subscribe(
      (member: MemberResponse) => {this.members = member.data}
    )
  }
  searchMember(){
    
    this.memberService.searchForMember(this.search).subscribe((member: MemberResponse)=>{
      this.members = member.data
    })
    console.log(this.search);
    
  }
  getCompetionsAfterToday(){
    this.competitionService.getCompetitions().subscribe((competition: CompetitionResponse) => {
      const tomorrow = new Date();
      tomorrow.setDate(tomorrow.getDate() + 1);
      this.competitionsFromOneDayNow = competition.data.filter(competition => {
        const compDate = new Date(competition.date)
        return compDate > tomorrow;
      })
    })
  }
  createMember(){
    if (this.addMemberForm.valid) {
      this.memberService.createMember(this.addMemberForm.value).subscribe({
        next: (val: any) => {
          this.toastr.success('Member created successfully.', 'Success', {
            closeButton: true,
            timeOut: 3000,
          });
          this.closeModalTrigger();
          this.getMembers();
          this.addMemberForm.reset();
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
  assignMemberForCompetition(){
    this.assignMemberForm.get('member_number')?.setValue(this.memberNumber)
    console.log(this.assignMemberForm.value);
    
    if (this.assignMemberForm.valid) {
      this.ranking.assignMemberForCompetition(this.assignMemberForm.value).subscribe({
        next: (val: any) => {
          this.toastr.success('Member assigned successfully for the competition', 'Success', {
            closeButton: true,
            timeOut: 3000,
          });
          this.closeModalTrigger2();
          //this.getMembers();
          this.assignMemberForm.reset();
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
  closeModalTrigger2(){
    if (this.closeModalButton2) {
      this.closeModalButton2.nativeElement.click();
    }
  }
  saveMemberNumber(memberNumber: number){
    this.memberNumber = memberNumber;
  }
}
