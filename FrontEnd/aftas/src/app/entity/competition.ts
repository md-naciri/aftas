import { Time } from "@angular/common";

export interface Competition {
    code: string,
    date: Date,
    Start_Time: Time, 
    End_Time: Time,
    Number_Of_Participants: number,
    location: string,
    amount: number
}
