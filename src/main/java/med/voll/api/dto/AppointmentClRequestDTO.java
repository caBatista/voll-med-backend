package med.voll.api.dto;

import med.voll.api.type.CancelReason;

public record AppointmentClRequestDTO(
	CancelReason cancelReason
){}
