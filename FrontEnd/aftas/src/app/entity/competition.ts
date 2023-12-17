import { Time } from "@angular/common";

export interface Competition {
    code: string,
    date: Date,
    startTime: Time, 
    endTime: Time,
    numberOfParticipants: number,
    location: string,
    amount: number
}
