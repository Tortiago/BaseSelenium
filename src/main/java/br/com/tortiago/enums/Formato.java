package br.com.tortiago.enums;

/**
 * {@link Enum} que padroniza os formatos de data/hora.
 */
public enum Formato {
	/**
	 * Formato de hora padrao (HH:mm:ss).
	 */
	HMS {
		@Override
		public String toString() {
			return "HH:mm:ss";
		}
	},
	HM {
		@Override
		public String toString() {
			return "HHmm";
		}
	},
	/**
	 * Formato ano-mes (yyyy-MM).
	 */
	YEAR_MONTH {
		@Override
		public String toString() {
			return "yyyy-MM";
		}
	},
	MONTH_YEAR {
		@Override
		public String toString() {
			return "MM/yyyy";
		}
	},
	/**
	 * Formato dia (dd).
	 */
	DAY {
		@Override
		public String toString() {
			return "dd";
		}
	},
	/**
	 * Formato mes (MM).
	 */
	MONTH {
		@Override
		public String toString() {
			return "MM";
		}
	},
	/**
	 * Formato ano (yyyy).
	 */
	YEAR {
		@Override
		public String toString() {
			return "yyyy";
		}
	},
	/**
	 * Formato de data padrao (dd/MM/yyyy).
	 */
	DDMMYYYY {
		@Override
		public String toString() {
			return "dd/MM/yyyy";
		}
	},
	/**
	 * Formato de data padrao (dd/MM/yyyy).
	 */
	DD_MM_YY {
		@Override
		public String toString() {
			return "dd_MM_yy";
		}
	},
	/**
	 * Formato de data inverso, separado por hifens (yyyy-MM-dd).
	 */
	YYYYMMDD {
		@Override
		public String toString() {
			return "yyyy-MM-dd";
		}
	},
	/**
	 * Formato de data inverso, separado por barras (yyyy/MM/dd).
	 */
	YMD {
		@Override
		public String toString() {
			return "yyyy/MM/dd";
		}
	},
	/**
	 * Formato de data/hora padrao ISO (dd/MM/yyyy HH:mm:ss).
	 */
	ISO_DATETIME {
		@Override
		public String toString() {
			return "dd/MM/yyyy HH:mm:ss";
		}
	};
}