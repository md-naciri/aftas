import { Component } from '@angular/core';
import { Ranking } from 'src/app/entity/ranking';
import { RankingResponse } from 'src/app/entity/ranking-response';
import { ViewChild, ElementRef } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { MemberService } from 'src/app/service/member.service';
import { RankingService } from 'src/app/service/ranking.service';

@Component({
  selector: 'app-rankings',
  templateUrl: './rankings.component.html',
  styleUrls: ['./rankings.component.css']
})
export class RankingsComponent {
  assignMemberForm: FormGroup;
  @ViewChild('closeModalButton', { static: false }) closeModalButton: ElementRef | undefined;
  constructor(
    private ranking: RankingService,
    private fb: FormBuilder,
    private toastr: ToastrService
  ){
    this.assignMemberForm = this.fb.group({
    member_number: "",
    competition_code: ""
    })
  }
  assignMemberForCompetition(){
    if (this.assignMemberForm.valid) {
      this.ranking.assignMemberForCompetition(this.assignMemberForm.value).subscribe({
        next: (val: any) => {
          this.toastr.success('Member assigned successfully for the competition', 'Success', {
            closeButton: true,
            timeOut: 3000,
          });
          this.closeModalTrigger();
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
}
