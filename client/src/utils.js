export function stringToColor(string) {
  let hash = 0;
  let i;

  /* eslint-disable no-bitwise */
  for (i = 0; i < string.length; i += 1) {
    hash = string.charCodeAt(i) + ((hash << 5) - hash);
  }

  let color = "#";

  for (i = 0; i < 3; i += 1) {
    const value = (hash >> (i * 8)) & 0xff;
    color += `00${value.toString(16)}`.slice(-2);
  }
  /* eslint-enable no-bitwise */

  return color;
}

export function ValidationError(error, helperText) {
  return {
    error: error,
    helperText: helperText,
  };
}

export function validateRequired(string) {
  return string.trim() !== "";
}

export function validateMinLength(string, length) {
  return string.length >= length;
}

export function validateNotEmpty(array) {
  if (!Array.isArray(array)) return false;
  return array.length > 0;
}
